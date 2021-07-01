$(document).ready(function(){
     $("#header").load("header.html");
});
function filterAccNo(){	
		 event.preventDefault();
		 let url = "AdminDisplay";
			fetch(url).then(res=>res.json()).then(res=>{
				let accno = document.querySelector("#accno").value;
				let users = res;
				if(accno != null){
		 users = res.filter(res =>JSON.stringify(res.accNo).includes(accno));
				}	 	
		 getDetails(users);	
			} )
	 }
	function updateStatus(accNo, status){
		let url = "AccountStatus?accno=" + accNo+"&status="+status;
		fetch(url).then(res=>res.json()).then(res=>{
			
			let result = res;
			
			if(result){
				alert("Success");
			}
			else{
				alert("Failed");
			}window.location.href="adminDisplay.html";
		})
	} 
	function getDetails(user){
	
		let users = user;
		console.log(users);
		let details = "";
		if(users.length!=0){
			for(let user of users){
			details += "<tr><td>" +user.name+ "</td>"+
			"<td>" + user.accNo + "</td>"+
			"<td>" + user.email+"</td>"+
			"<td>"+user.mobileNo+"</td>"+
			"<td>"+user.address+"</td>"+
			"<td>"+user.balance+"</td>"+
			"<td>"+user.createdDate+"</td>";
			if(user.active){
				details+="<td class='badge badge-pill badge-success'>"+"Active"+"</td>";
			}
			else{
				details+="<td class='badge badge-pill badge-danger'>"+"Inactive"+"</td>";
			}
			if(user.active){
			details+="<td><button type ='button'class='btn btn-danger' onclick=\"updateStatus(" + user.accNo + ",false)\">DeActivate</button></td></tr>";
			
			}
			else{
				details+="<td><button type ='button' class = 'btn btn-success' onclick=\"updateStatus(" + user.accNo + ",true)\">Activate</button></td></tr>";
			}

			}}else{
				details+="<tr><td colspan=14 class='text-center'>" + "No Records Found" + "</td></tr>";
			}
		
		
		document.querySelector("#details").innerHTML = details;
	}
