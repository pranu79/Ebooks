$(function() {

	var $registerForm = $("#register");
	

	$registerForm.validate({
		rules: {
			name: {
				required: true,
				
			},

			email: {
				required: true,
				email: true,
				all:true
			},

			phno: {
				required: true,
				all:true

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
				required: true
			}

		},
		messages: {
			name: {
				required: "Name is required"
			},
			email: {
				required: "Email is required",
				email: "Please enter vaild email address",
				all:"spaces are not allowed"
			},
			phno: {
				required: "Phone number is required",
				minlength: "minimum 10 digits required",
				all:"spaces are not allowed"

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
				required: "city name is required"
			},
			zip: {
				required: "pincode is required"
			}

		}
	})
	jQuery.validator.addMethod("all", function(value, element) {
		return /^[^-\s][a-zA-Z0-9_\s-]+$/.test(value);
	})

})

const payment = () => {

	console.log("helo");

}

