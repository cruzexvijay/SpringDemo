console.log("document loaded");
"use strict";


$("document").ready(function(){
	$('li').click(function(e){
		var id = $(this).attr("id");
		
		$(("#"+id)).attr("class","active").siblings().removeClass("active");
	});
});

var app = angular.module("adminApp",["ui.router","ngResource"]);

app.config(function($stateProvider){
	console.log("route config loaded");
	
	var dashboardState = {
		name:"dashboard",
		url:"/",
		templateUrl:"dashboard.html",
		controller:"dashboardController"
	}
	
	var candidateState = {
			name : "candidates",
			url:"/candidates",
			templateUrl : "candidates.html",
			controller:"candidateController"
	}
	
	var loadState = {
			name:"load",
			url:"/load",
			templateUrl:"load.html"
	}
	
	$stateProvider.state(dashboardState);
	$stateProvider.state(candidateState);
	$stateProvider.state(loadState);
});

app.controller("breadCrumbController",function($scope){
	console.log("bc controller");
	$scope.breadCrumbs = ["Dashboard"];
});

app.controller("onGoingController",function($scope){
	$scope.events=["event1","event2","event3"];
});

app.controller("dashboardController",function($scope){
	console.log("dashboard Controller");
	$scope.message="Welcome";
});

app.controller("candidateController",function($scope,CandidateFactory){
	$scope.candidates=[];
	//$scope.candidates.push({candidateId:"1234",firstName:"Vijay",lastName:"Kumar",status:"Passed"});
	
	CandidateFactory.query(function(data){
		$scope.candidates=data;
	});
});

app.factory("CandidateFactory",function($resource){
	return $resource("http://localhost:8080/WebService/rest/candidate/all");
})
