console.log("hi there")

var app = angular.module("myApp",[]);

app.controller("modAcontroller",function($scope){
	console.log("modAcontroller")
	$scope.firstName = "vijay";
	$scope.lastName = "kumar";
});

app.controller("modBcontroller",function($scope){
	console.log("modBController");
	$scope.msg = "Welcome to Angular JS";
});

app.controller("modCcontroller",function($scope){
	console.log("repeatController");
	$scope.names = ["Candidate1","candidate2","candidate3"];
});