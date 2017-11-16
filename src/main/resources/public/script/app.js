var app = angular.module('b2s-wiki', ['ngRoute']);

app.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/list.html',
                controller: 'ListController'
            })
            .when('/add', {
                templateUrl: 'views/add.html',
                controller: 'AddController',
            })
            .when('/detail/:id', {
                templateUrl: 'views/detail.html',
                controller: 'DetailController',
            })
            .when('/edit/:id', {
                templateUrl: 'views/add.html',
                controller: 'AddController',
            });
    }])

app.filter('trusted', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
}]);

app.controller('BaseController', function ($http, $scope) {
    window.onscroll = function () {
        var t = document.documentElement.scrollTop || document.body.scrollTop;
        if (t > 0) {
            $("#header").addClass("header-shadow");
        } else {
            $("#header").removeClass("header-shadow");
        }
    }
});

app.controller('ListController', function ($http, $scope, $location) {
    $scope.keyword = "";
    function reset() {
        $scope.total = 0;
        $scope.index = 0;
        $scope.articles = [];
    }

    $scope.search = function (clear) {
        if (clear) {
            reset();
        }
        $http.get("/article", {params: {keyword: $scope.keyword, index: $scope.index}}).then(
            function (res) {
                var pager = res.data;
                $scope.total = pager.total;
                $scope.index = pager.currentIndex;
                $scope.articles = $scope.articles.concat(pager.articles);
            });
    }

    $scope.detail = function (doc) {
        window.open('/#!/detail/' + doc.id, '_blank');
    }

    $scope.edit = function(doc) {
        $location.path("/edit/" + doc.id);
    }

    $scope.delete = function(doc) {
        if(confirm("Do you want to delte this article?")) {
            $http.delete("/article/" + doc.id).then(
                function (res) {
                    init();
                });
        }
    }

    $scope.new = function() {
        $location.path("/add");
    }

    function init() {
        $scope.search(true);
    };
    init();
});

app.controller('AddController', function ($http, $scope, $location, $routeParams) {
    var ue;
    $scope.article = {};
    $scope.submit = function () {
        if (valid()) {
            $http.post("/article", {id: $scope.article.id, title: $scope.article.title, content: ue.getContent()}).then(
                function (res) {
                    $location.path("/");
                });
        }
    }
    function valid() {
        return $scope.article.title && ue.getContent();
    }

    function init() {
        new UE.ui.Editor().render('ue-container');
        ue = UE.getEditor('ue-container');
        if($routeParams.id) {
            $http.get("/article/" + $routeParams.id).then(
                function (res) {
                    $scope.article = res.data;
                    ue.ready(function(){
                        ue.setHeight(400);
                        ue.setContent(res.data.content);
                    })
                });
        } else {
            ue.ready(function(){
                ue.setHeight(400);
            })
        }
    };
    init();
});

app.controller('DetailController', function ($http, $scope, $routeParams) {
    $scope.article;
    function init() {
        $http.get("/article/" + $routeParams.id).then(
            function (res) {
                $scope.article = res.data;
            });
    };
    init();
});
