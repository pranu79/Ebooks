$(function() {

	var $checkout = $("#checkout");

	$checkout.validate({

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

			address: {
				required: true
			},

			landmark: {
				required: true
			},
			state: {
				required: true
			},

			city: {
				required: true
			},
			zip: {
				required: true,
				minlength: 6
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
			address: {
				required: "Address is required"
			},

			landmark: {
				required: "Landmark is required"
			},
			state: {
				required: "State name is required"
			},

			city: {
				required: "City name is required"
			},

			zip: {
				required: "Zip code is required",
				minlength: "Minimum 6 digits required"
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










