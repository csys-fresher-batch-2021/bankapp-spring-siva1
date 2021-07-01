function restrict() {
	let user = localStorage.getItem("LOGGED_IN_USER");
	user = JSON.parse(user);
	console.log(user);

	if (user!=null && user !="Admin") {
	
		console.log("if");
		document.querySelectorAll(".nav-link[data-loggedIn=true]").forEach(obj => obj.style.display = "block");
		document.querySelectorAll(".nav-link[data-loggedIn=user]").forEach(obj => obj.style.display = "block");
		document.querySelectorAll(".nav-link[data-loggedIn=false]").forEach(obj => obj.style.display = "none");
	} else if (user!=null && user=="Admin") {
		console.log("else  if");
		document.querySelectorAll(".nav-link[data-loggedIn=true]").forEach(obj => obj.style.display = "block");
		document.querySelectorAll(".nav-link[data-loggedIn=user]").forEach(obj => obj.style.display = "none");
		document.querySelectorAll(".nav-link[data-loggedIn=false]").forEach(obj => obj.style.display = "none");
	} else {
		console.log("else");
		document.querySelectorAll(".nav-link[data-loggedIn=false]").forEach(obj => obj.style.display = "block");
		document.querySelectorAll(".nav-link[data-loggedIn=true]").forEach(obj => obj.style.display = "none");
		document.querySelectorAll(".nav-link[data-loggedIn=user]").forEach(obj => obj.style.display = "none");
		console.log("true");
		 //$(".nav-link[data-loggedIn=false]").show();
		 //$(".nav-link[data-loggedIn=manager]").hide();
		 //$(".nav-link[data-loggedIn=true]").hide();
		 //$(".nav-link[data-loggedIn=user]").hide();
	}
	
}
restrict();
function logout(){
let url = "Logout";
axios.get(url).then(res=>{
});
localStorage.clear();
window.location.href = "header.html";

}


