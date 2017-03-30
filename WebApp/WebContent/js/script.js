var app = angular.module("mjolnir",["ui.router"]);

app.config(function($stateProvider){
	var loadState = {
			name:"load",
			url:"#load",
			templateUrl:"dashboard.html"
	}
	
	$stateProvider.state(loadState);
});

app.controller("loadController",function($scope){
	console.log("loadController");
	$scope.message = "Welcome";
});