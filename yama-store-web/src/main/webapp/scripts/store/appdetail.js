//var app = angular.module('qstore', []);
//
//
//app.controller('qstore-catalog', ['$scope', '$http', '$sce', '$filter',
//                                  function($scope, $http, $sce, $filter) {
// 	$scope.toTrusted = function(n) {
// 		return $sce.trustAsHtml($filter('limitTo')(n, 600));
// 	};
// 	
// 	$http.get(ns + 'p.json?max=100&c=' + ct + '&q=' + q).success(function(data) {
// 		console.log(data);
// 		$scope.products = data.products.content;
// 	});
// 	
// }]);
//
//app.controller('qstore-category', [ '$scope', '$http', function($scope, $http) {
//	$http.get(ns + 'c.json').success(function(data) {
// 		console.log(data);
//		$scope.categories = data.categories.content;
//	});
//}]);
//
//app.controller('qstore-product-detail', ['$scope', '$http', '$sce', '$filter',
//                                 function($scope, $http, $sce, $filter) {
//	$http.get(ns + 'p/api/product/' + id + '.json').success(function(d) {
//		$scope.product = d;
//	});
//}]);
var app = angular.module('qstore', []);

app.factory('productService', ['$http', function($http) {
	return {
		find : function(id, callback) {
			$http.get(ns + 'api/p/' + id + '/detail.json').success(callback);
		}
	};
}]);

app.factory('categoryService', ['$http', function($http) {
	return {
		find : function(callback) {
			$http.get(ns + 'api/c.json').success(callback);
		}
	};
}]);

app.controller('qstore-product-detail', ['$scope', '$http', '$sce', '$filter', 'productService', 'categoryService',
                          function($scope, $http, $sce, $filter, productService, categoryService) {
 	$scope.toTrusted = function(n) {
 		return $sce.trustAsHtml($filter('limitTo')(n, 600));
 	};
 	
 	categoryService.find(function(data) {
		$scope.categories = data.categories.content;
		
		productService.find(id, function(data) {
	 		$scope.product = data.product;
	 	});
	});
 	
 }]);