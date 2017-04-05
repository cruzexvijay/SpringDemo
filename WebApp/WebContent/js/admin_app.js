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
		ModalService, MjolnirService) {

	$scope.schedules = [{date:"1/2/2013",time:{hours:"2",mins:"30",am_pm:"PM"},location:"MBP",medium:"Skype",notes:"This is a very good notes"}];
	$scope.showToggle = true;
	
	$scope.show = function(candidate) {
		ModalService.showModal({
			templateUrl : "modal.html",
			controller : "modalController",
			size : "lg"
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
	$scope.showScheduleForm = function(){
		
		console.log("newSchedule");
		$scope.showToggle=!$scope.showToggle;
	};

	$scope.newSchedule = function() {

		ModalService.showModal({
			templateUrl : "modal.html",
			controller : "modalController",
		}).then(function(modal) {
			modal.element.modal();
		});

		MjolnirService.setModalTitle("New Schedule");
		MjolnirService.setCandidate(null);
	};

	$scope.edit = function(schedule){
		$scope.showToggle=true;
		console.log("edit");
		console.log(schedule);
	};
	
	$scope.delete = function(schedule){
		$scope.showToggle = !$scope.showToggle;
	};
	
	$scope.showNotes = function(schedule){
		console.log("showing notes");
		$("p").text(schedule.notes);
	};
});

app.service("MjolnirService", function() {
	var candidate = {};
	var modalTitle = "";

	this.setCandidate = function(value) {
		this.candidate = value;
	},

	this.getCandidate = function() {
		return this.candidate;
	},

	this.setModalTitle = function(title) {
		this.modalTitle = title;
		// console.log("New title set "+this.modalTitle);
	},

	this.getModalTitle = function() {
		return this.modalTitle;
	}
});

app.controller("modalController", function($scope, ScheduleFactory,
		CandidateFactory, MjolnirService) {

	console.log("modalcontroller");

	$scope.title = MjolnirService.getModalTitle();
	$scope.candidate = MjolnirService.getCandidate();
	$scope.btn_text = "Submit";

	// console.log($scope.candidate);

	if ($scope.candidate) {
		var id = $scope.candidate.candidateId;

		ScheduleFactory.get({
			id : id
		}, function(data) {
			console.log("getting schedules of candidate");
			console.log(data);
		});
	}

	// var getc = CandidateFactory.get({id:"1"});

	// var getc = ScheduleFactory.query({filter:"completed"});
	var getc = ScheduleFactory.get({
		id : "2"
	});

	console.log("loading");
	console.log(getc);

	$scope.isEnabled = false;

	if ($scope.candidate) {
		$scope.isEnabled = true;
	}

	$scope.close = function(msg) {
		console.log(msg);
		MjolnirService.setCandidate(null);
	}

	$scope.submit = function() {
		var cand = $scope.candidate;
		console.log(cand);
	}

	$scope.edit = function() {
		$scope.isEnabled = !$scope.isEnabled;
		$scope.btn_text = "Update";
		// console.log("enabling");
	}
});

app.service("ScheduleService", function(CandidateFactory, ScheduleFactory) {

	this.getEvents = function(filter) {

		console.log("getting events");

		var events = [];

		ScheduleFactory.query({
			filter : filter
		}, function(data) {
			angular.forEach(data, function(event) {
				CandidateFactory.get({
					id : event.candidateId
				}, function(candidate) {
					var candidateName = candidate.firstName + " "
							+ candidate.lastName;
					event.candidateName = candidateName;
					event.dateTime = new Date(event.dateTime);

					var color = "success";

					if (event.medium == "Skype") {
						color = "info";
					}

					event.color = color;
					events.push(event);
				});
			});

		});
		return events;
	};

});

app.controller("completedController", function($scope, ScheduleService) {
	$scope.events = ScheduleService.getEvents("completed");
});

app.controller("breadCrumbController", function($scope) {
	$scope.breadCrumbs = [ "Dashboard" ];
});

app.controller("onGoingController", function($scope, ScheduleService) {
	var events = ScheduleService.getEvents("ongoing");
	$scope.onGoingEvents = events;

});

app.controller("upComingController", function($scope, ScheduleService) {
	$scope.upComingEvents = ScheduleService.getEvents("upcoming");
});

app.controller("dashboardController", function($scope) {
	console.log("dashboard Controller");
	$scope.message = "Welcome";
});

app.controller("candidateController", function($scope, $rootScope,
		CandidateFactory, ModalService, MjolnirService) {
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
			modal.closed.then(function(data, val) {
				// console.log("closed");
				MjolnirService.setCandidate(null);
			});
		});

		MjolnirService.setCandidate(candidate);
		MjolnirService.setModalTitle("Candidate Details");
	};

});

var resourceErrorHandler = function(response) {
	console.log("error in response");
	console.log(response.data);
}

app.factory("CandidateFactory", function($resource) {
	return $resource("http://localhost:8080/WebService/rest/", {}, {
		query : {
			method : "GET",
			url : "http://localhost:8080/WebService/rest/candidate/all",
			isArray : true,
			interceptor : {
				responseError : resourceErrorHandler
			}
		},
		get : {
			method : "GET",
			url : "http://localhost:8080/WebService/rest/candidate/:id",
			interceptor : {
				responseError : resourceErrorHandler
			}
		},
		save : {
			method : "POST",
			url : "http://localhost:8080/WebService/rest/candidate/new",
			interceptor : {
				responseError : resourceErrorHandler
			}
		}
	});
});

app.factory("ScheduleFactory", function($resource) {
	return $resource("http://localhost:8080/WebService/rest/", {}, {
		query : {
			method : "GET",
			url : "http://localhost:8080/WebService/rest/schedule?q=:filter",
			isArray : true,
			param : {
				filter : '@filter'
			},
			interceptor : {
				responseError : resourceErrorHandler
			}
		},
		get : {
			method : "GET",
			url : "http://localhost:8080/WebService/rest/schedule/:id",
			interceptor : {
				responseError : resourceErrorHandler
			}
		},
		create : {
			method : "POST",
			url : "http://localhost:8080/WebService/rest/schedule/:id/new",
			param : {
				id : '@id'
			},
			interceptor : {
				responseError : resourceErrorHandler
			}
		}

	});
});
