$(document).ready(function() {
	$("#header").load("header.html");
});
let accountNumber = localStorage.getItem("ACCOUNTNUMBER");
console.log(accountNumber);
document.querySelector("#accno").value = accountNumber;
function withdraw() {
	event.preventDefault();
	// Step 1: Get Form Values
	let withdraw = document.querySelector("#withdrawForm");
	let amount = document.querySelector("#amount").value;
	let accno = document.querySelector("#accno").value;
	let url = "Withdraw?accno=" + accno + "&amount=" + amount;
	console.log(amount);
	// 2. Check form is valid
	if (withdraw.checkValidity()) {
		withdraw.classList.remove("was-validated");
		axios.patch(url).then(res => {

			let data = res.data.infoMessage;
			console.log(data);
			window.location.href = "summary.jsp?infoMessage=" + "WITHDRAW SUCCESS";
		})
			.catch(err => {
				let data = err.response.data.errorMessage;
				document.querySelector("#message").innerHTML = data;
			});
	} else {
		withdraw.classList.add("was-validated");
		//field errors
		if (amount.value.length == 0) {
			amount.classList.add("is-invalid");
		} else {
			amount.classList.add("is-valid");
		}
	}
}