
function buttonLoginClick(){
	email = $('#formLogin-email').val();
	lozinka = $('#formLogin-password').val();
	if(!email || !lozinka) {
		toastr["error"]('Fill in all required entry fields!');
		return;
	}
	var data = JSON.stringify([
	             {"fieldId":"email",
	            "fieldValue":email},
	            {"fieldId":"lozinka",
		            "fieldValue":lozinka},
		            
	             ]);
	
	var emailCheck = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
	var res = email.match(emailCheck);          
	
	if(res !=null){
			$.ajax({
				async: false,
				url: "http://localhost:4242/user/loginUser",
		        type: "POST",
		        contentType:"application/json",
		        data : data,
		        crossDomain: true,
		        withCredentials: true,
		        headers: {  'Access-Control-Allow-Origin': '*' },
		        success: function (data2) {
	         		if(data2!=null){
	         			if(data2.uloga=="UREDNIK" && data2.isGlavni == true){
	         				top.location.href= "homeGlavni.html";
	         			}
	         			else if(data2.uloga=="RECENZENT"){
	         				top.location.href = "homeRecenzent.html";
	         			}	
		        		else{
		        			top.location.href = "NC.html";
		        		}
	         		}else{
	         			top.location.href="registracija.html";
	         		}
	
                },
                error: function (jqxhr, textStatus, errorThrown) {
                	toastr['error']('Greska!');
                } 
            });	
	}else{
		toastr['error']('Invalid email');
	}

  }
	

