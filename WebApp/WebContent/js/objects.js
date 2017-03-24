
var Candidate = {
	id : "",
	firstName : "",
	lastName : "",
	emailId:"",
	contactNumber : "",
	setContactId : function(value) {
		this.id = value;
	},
	getContactId : function() {
		return this.id;
	},
	setFirstName : function(value) {
		this.firstName = value;
	},
	getFirstName : function() {
		return this.firstName;
	},
	setLastName : function(value) {
		this.lastName = value;
	},
	getLastName : function() {
		return this.lastName;
	},
	setContactNumber : function(value) {
		this.contactNumber = value;
	},
	getContactNumber : function() {
		return this.contactNumber;
	},
	setEmailId : function(value) {
		this.emailId = value;
	},
	getEmailId : function() {
		return this.contactNumber;
	}
}

$(document).ready(function() {
	$('#newTestbtn').click(function() {
		alert('creating new test');
	});

});

