console.log("document loaded");

var app = angular.module("adminApp",["ngResource","angularModalService"]);

app.controller("breadCrumbController",function($scope){
	console.log("bc controller");
	$scope.breadCrumbs = ["Dashboard"];
});

app.controller("onGoingController",function($scope){
	$scope.events=["event1","event2","event3"];
});