$(document).ready(function(){
     $("#header").load("header.html");
});
function login() {
			event.preventDefault();
			// Step 1: Get Form Values
			let loginForm = document.querySelector("#loginForm");
			let username = document.querySelector("#email").value;
			let password = document.querySelector("#password").value;
			let url = "UserLogin";
			let user={
					
					"email":username,
					"password":password
			};
			console.log(user);
			  console.log(username + "-" + password);
				// 2. Check form is valid
				if (loginForm.checkValidity()) {
					loginForm.classList.remove("was-validated");
					axios.post(url,user).then(res=>{
						alert("Login Success");
						let data=res.data;
						let accno = res.data.accNo;
						console.log(accno);
						let name = JSON.stringify(res.data.name);
						console.log(name);
						localStorage.setItem("LOGGED_IN_USER",name);
						localStorage.setItem("ACCOUNTNUMBER",accno);
						console.log(res);
						window.location.href="display.html";
					})
					.catch(err=>{
						let data = err.response.data.errorMessage;
				document.querySelector("#message").innerHTML= data;
			});

				} else {
					loginForm.classList.add("was-validated"); 
					//field errors
					if (username.value.length == 0) {
						username.classList.add("is-invalid");
					}
					else{
						username.classList.add("is-valid");
					}
				}  
		}