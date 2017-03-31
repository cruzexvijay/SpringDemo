console.log("document loaded");
"use strict";

$("document").ready(function() {
	$('li').click(function(e) {
		var id = $(this).attr("id");

		$(("#" + id)).attr("class", "active").siblings().removeClass("active");
	});
});

var app = angular.module("adminApp", [ "ui.router", "ngResource",
		"angularModalService" ]);

app.filter('inRange', function() {
	return function(input) {
		var lowBound, highBound;
		switch (input.length) {
		case 1:
			lowBound = 0;
			highBound = parseInt(input[0]) - 1;
			break;
		case 2:
			lowBound = parseInt(input[0]);
			highBound = parseInt(input[1]);
			break;
		default:
			return input;
		}
		var result = [];
		for (var i = lowBound; i <= highBound; i++)
			result.push((i > 9) ? i : ("0" + i));
		return result;
	};
});

app.config(function($stateProvider) {
	console.log("route config loaded");

	var dashboardState = {
		name : "dashboard",
		url : "/",
		templateUrl : "dashboard.html",
		controller : "dashboardController"
	}

	var candidateState = {
		name : "candidates",
		url : "/candidates",
		templateUrl : "candidates.html",
		controller : "candidateController"
	}

	var loadState = {
		name : "load",
		url : "/load",
		templateUrl : "load.html"
	}

	$stateProvider.state(dashboardState);
	$stateProvider.state(candidateState);
	$stateProvider.state(loadState);
});

app.controller("newScheduleController", function($scope, $rootScope,
		ModalService,MjolnirService) {

	$scope.show = function(candidate) {
		ModalService.showModal({
			templateUrl : "modal.html",
			controller : "modalController",
			size : "lg"
		}).then(function(modal) {
			modal.element.modal();
		});

	};

	$scope.newSchedule = function(){
		
		ModalService.showModal({
			templateUrl : "modal.html",
			controller:"modalController",
		}).then(function(modal){
			modal.element.modal();
		});
		
		MjolnirService.setModalTitle("New Schedule");
		MjolnirService.setCandidate(null);
	};

});

app.service("MjolnirService",function(){
	var candidate={};
	var modalTitle="";
	
	this.setCandidate = function(value){
		this.candidate = value;
	},
	
	this.getCandidate = function(){
		return this.candidate;
	},
	
	this.setModalTitle = function(title){
		this.modalTitle = title;
		//console.log("New title set "+this.modalTitle);
	},
	
	this.getModalTitle = function(){
		return this.modalTitle;
	}
});

app.controller("modalController", function($scope,CandidateFactory,MjolnirService) {
	
	console.log("modalcontroller");

	$scope.title = MjolnirService.getModalTitle();	
	$scope.candidate = MjolnirService.getCandidate();
	$scope.btn_text = "Submit";
	
	$scope.isEnabled = false;
	
	if($scope.candidate){
		$scope.isEnabled=true;
	}
		
	$scope.close = function(msg) {
		console.log(msg);
		MjolnirService.setCandidate(null);
	}

	$scope.submit = function() {
		console.log("submitting....");
	}

	$scope.edit = function(){
		$scope.isEnabled=!$scope.isEnabled;
		$scope.btn_text="Update";
		console.log("enabling");
	}
});

app.controller("breadCrumbController", function($scope) {
	console.log("bc controller");
	$scope.breadCrumbs = [ "Dashboard" ];
});

app.controller("onGoingController", function($scope) {
	$scope.events = [ "event1", "event2", "event3" ];
});

app.controller("dashboardController", function($scope) {
	console.log("dashboard Controller");
	$scope.message = "Welcome";
});

app.controller("candidateController", function($scope, $rootScope,
		CandidateFactory, ModalService,MjolnirService) {
	$scope.candidates = [];
	// $scope.candidates.push({candidateId:"1234",firstName:"Vijay",lastName:"Kumar",status:"Passed"});

	CandidateFactory.query(function(data) {
		$scope.candidates = data;
	});

	$scope.show = function(candidate) {
		
		ModalService.showModal({
			templateUrl : "modal.html",
			controller : "modalController",
		}).then(function(modal) {
			modal.element.modal();
			modal.closed.then(function(data,val){
				console.log("closed");
				MjolnirService.setCandidate(null);
			});
		});

		MjolnirService.setCandidate(candidate);
		MjolnirService.setModalTitle("Candidate Details");
	};

});

app.factory("CandidateFactory", function($resource) {
	return $resource("http://localhost:8080/WebService/rest/", {}, {
		query : {
			method : "GET",
			url : "http://localhost:8080/WebService/rest/candidate/all",
			isArray : true,
		},
		get : {
			method : "GET",
			url : "http://localhost:8080/WebService/rest/candidate/:id",
		},
		create:{
			method:"POST",
			url:"http://localhost:8080/WebService/rest/candidate/",
		}
	});
});
