$(document).ready(function() {
	$("#header").load("header.html");
});

function getAllDetails() {
	let accNo = localStorage.getItem("ACCOUNTNUMBER");
	console.log(accNo);
	let url = "Display?accno=" + accNo;
	fetch(url).then(res => res.json()).then(res => {
		let user = res;
		console.log(user);
		let details = "";

		details += "<tr><td>" + user.name + "</td>" +
			"<td>" + user.accNo + "</td>" +
			"<td>" + user.email + "</td>" +
			"<td>" + user.mobileNo + "</td>" +
			"<td>" + user.address + "</td>" +
			"<td>" + "Rs." + user.balance + "</td>" +
			"<td>" + user.createdDate + "</td>";
		if (user.active) {
			details += "<td class='badge badge-pill badge-success'>" + " Active" + "</td>";
		}
		else {
			details += "<td class='badge badge-pill badge-danger'>" + "Inactive" + "</td>";
		}
		if (user.active) {
			details += "<td><button type ='button'class='btn btn-danger' onclick=\"updateAccountDetails(" + user.accNo + ",false)\">DeActivate</button></td></tr>";

			details += "<tr><td><a href='deposit.html' class='btn btn-warning'>Deposit</a></td>"
				+ "<td><a href='withdraw.html' class='btn btn-warning'>Withdraw</a></td>"
				+ "<td><a href='transferAmount.html' class='btn btn-warning'>Transfer Amount</a></td>"
				+ "<td><a href='transactionSummary.html' class='btn btn-warning'>Statement</a></td>"
				+ "<td><a href='miniStatement.html' class='btn btn-warning'>MiniStatement</a></td></tr>";
		}
		else {
			details += "<td><button type ='button' class = 'btn btn-success' disabled onclick=\"updateAccountDetails(" + user.accNo + ",true)\">Activate</button></td></tr>";


		}
		document.querySelector("#userlist").innerHTML = details;
	})

}
function updateAccountDetails(accNo, status) {
	//let url ="AccountStatus/" + accNo+"/"+status;
	let url = "AccountStatus?accno=" + accNo + "&status=" + status;
	console.log(url);
	fetch(url).then(res => res.json()).then(res => {

		let result = res;
		console.log(result);
		if (result == true) {
			alert("Success");
		}
		else {
			alert("Failed");
		} window.location.href = "display.html";
	})
}
getAllDetails();
