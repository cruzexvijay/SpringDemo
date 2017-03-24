var app = angular.module("myApp", []);

app.controller("modController", function($scope) {
	var names = [ 'candidate1', 'emp1' ];
	$scope.names = names;
});

app.controller("onGoingController", function($rootScope, $scope) {

	$scope.schedules = [ person1, person2 ];

	$scope.showCandidateDetails = function(candidate) {
		var id = candidate.candidateId;

		$(document).ready(function() {
			$(".candidateInfo").attr("class", "candidateInfo").show(function() {
				console.log("animation completed");
				$("#btn-controls").show(500);
			});

		});

		console.log("broadcasting candidateInfoClickEvent");
		$rootScope.$broadcast("candidateInfoClickEvent", candidate);

	};

});

app.controller("candidateInfoController", function($scope) {
	$scope.$on("candidateInfoClickEvent", function(event, data) {
		console.log(data);
		$scope.candidate = data;
	});
});

app.controller("upNextController", function($scope) {
	console.log("fetching data..");
	$scope.val = "Hi";

});

app.controller("showMsgController", function($q, $scope, $http,
		candidateInfoRequestService) {

	$scope.val = "hi";
	console.log("fetching data");
	var deferred = $q.defer();

	$scope.candidates=[];

	var content = candidateInfoRequestService.getAllCandidates().then(
			function(data) {
				angular.forEach(data,function(val){
					console.log(val);
					$scope.candidates.push(val);
				});
			});

	
});

app.service("candidateInfoRequestService", function($http) {
	console.log("service");
	this.getAllCandidates = function() {
		return $http.get(
				"http://localhost:8080/WebService/rest/Candidate/showAll")
				.then(function(response) {
					return response.data;
				});
	};
});

app.controller("completedController", function($scope) {

	$scope.schedules = [ person1, person2 ];

});

