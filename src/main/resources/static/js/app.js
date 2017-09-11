var myApp = angular.module('myApp',[]);

myApp.controller('decryptController',['$scope','$http',function($scope,$http){
	$scope.encryptedStr = '';
	$scope.decryptedStr = '';
    $scope.errorMsg = null;

	$scope.decrypt = function(){
        $scope.decryptedStr = '';
        $scope.errorMsg = null;
		$http.post('odidecrypt', $scope.encryptedStr)
		.then(function successCallback(response) {
			$scope.decryptedStr = response.data.decryptedStr;
		}, function errorCallback(response) {
			$scope.errorMsg = response.data.error;
		});
	}
}]);