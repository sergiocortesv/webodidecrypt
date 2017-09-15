var myApp = angular.module('myApp',['ngRoute','ngFileUpload']);

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

myApp.controller('odiXmlDecryptController',['$scope','$http','Upload',function($scope,$http,Upload){
	$scope.submit = function(){
      if($scope.form.file.$valid && $scope.file)  {
          $scope.upload($scope.file);
      }
    };
    
    // upload on file select or drop 
    $scope.upload = function (file) {
        Upload.upload({
            url: 'xmlodidecrypt',
            data: {file: file}
        }).then(function (resp) {
            console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
            $scope.snpLogins = resp.data;
        }, function (resp) {
            console.log('Error status: ' + resp.status);
        }, function (evt) {
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
        });
    };
    
}]);

myApp.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $routeProvider
  .when('/', {
	    templateUrl: 'pages/odiDecrypt.html',
	    controller: 'decryptController'
  })
  .when('/xmlOdiDecrypt',{
        templateUrl: 'pages/odiXmlDecrypt.html',
	    controller: 'odiXmlDecryptController'
  });
  $locationProvider.hashPrefix('');
}]);