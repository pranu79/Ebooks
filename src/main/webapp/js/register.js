$(function() {

	var $registerForm = $("#register");


	$registerForm.validate({
		rules: {
			name: {
				required: true,
				lettersonly: true

			},

			email: {
				required: true,
				email: true

			},

			phno: {
				required: true,
				numericonly: true,
				all: true,
				minlength: 10
			},
			password: {
				required: true,
				all: true
			}


		},
		messages: {
			name: {
				required: "Name is required",
				lettersonly: "Only letters are allowed"
			},
			email: {
				required: "Email is required",
				email: "Please enter vaild email address",

			},
			phno: {
				required: "Phone number is required",
				numericonly: "Only digits are allowed",
				minlength: "minimum 10 digits required",
				all: "spaces are not allowed"


			},
			password: {
				required: "Password is required",
				all: "spaces is not allowed"
			}

		}
	})
	jQuery.validator.addMethod("all", function(value, element) {
		return /^[^-\s][a-zA-Z0-9_\s-]+$/.test(value);
	});

	jQuery.validator.addMethod("numericonly", function(value, element) {
		return /^[0-9]+$/.test(value);
	});

	jQuery.validator.addMethod("lettersonly", function(value, element) {
		return /^[^-\s][a-zA-Z_\s-]+$/.test(value);
	});

})

$(function() {
	var $login = $("#login");

	$login.validate({
		rules: {

			email: {
				required: true,
				email: true

			},
			password: {
				required: true,
				all: true
			}

		},
		messages: {

			email: {
				required: "Email is required",
				email: "Please enter vaild email address",

			},
			password: {
				required: "Password is required",
				
			}

		}
	})


})