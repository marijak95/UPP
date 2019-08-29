$(document).ready(function () {
	var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PID");
	$.ajax({
		async: false,
		url: "http://localhost:4242/user/getFormFields/"+PInsId,
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	divGlavni = $('#formaKoautor');
        	var str="";
        	
        	for(i=0;i<data.formField.length;i++){
        		if(data.formField[i].id != "josKoautora"){
	        		str+="<div class=\"form-group\">";
	        		str+="<label>"+data.formField[i].label+"</label>";
	        		str+="<input type=\"text\"  placeholder="+data.formField[i].label+" class=\"form-first-name form-control\" id="+data.formField[i].id+">";
	        		str+="</div>";
	        		divGlavni.append(str);
	        		
	        		str="";
        		}else{
        			str+="<div class=\"form-group\">";
	        		str+="<label>"+data.formField[i].label+"</label>";
        			/*str+="<input type=\"checkbox\" id=\""+ data.formField[i].id + "\"/>";*/
        			str+="</div>";
	        		divGlavni.append(str);
	        		
	        		str="";
        		}
        	}
        	str+=" <button type=\"button\" onclick=\"dodajKoautora()\" class=\"button-home\">Dodaj koautora</button>";
        	
        	str+="<input type=\"hidden\" id=\"taskId\" name=\"taskId\" value=\""+data.taskId+"\">";
			str+="<input type=\"hidden\" id=\"processInstanceId\" name=\"processInstanceId\" value=\""+data.processInstanceId+"\">";
			
        	divGlavni.append(str);
        	
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Greska!');
        } 
        });	
})

function dodajKoautora(){
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PID");
	
	ime = $("#ime").val();
	prezime = $("#prezime").val();
	
	email = $('#email').val();
	grad = $('#grad').val();
	drzava = $("#drzava").val();
	
	//josKoautora = $('#josKoautora').is(":checked");
	
	if(!email || !ime || !prezime ||  !grad || !drzava) {
		toastr["error"]('Popunite sva polja za koautora!');
		return;
	}
	
	var data = JSON.stringify([
	            {"fieldId":"ime",
	            	"fieldValue":ime},
	            	{"fieldId":"prezime",
		            	"fieldValue":prezime},
	            	{"fieldId":"email",
		            	"fieldValue":email},
		            	{"fieldId":"drzava",
			            	"fieldValue":drzava},
			            	{"fieldId":"grad",
				            	"fieldValue":grad}/*,
				            	{"fieldId":"josKoautora",
					            	"fieldValue":josKoautora}*/
	                           ]);
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/rad/dodajKoautora/"+PInsId,
        type: "POST",
        contentType:"application/json",
        data : data,
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data1) {
        	if(data1 === "true"){
        		toastr["info"]("Koautor dodat. Nastavite sa dodavanjem..");
        		top.location.href = "dodavanjeKoautora.html?PID="+PInsId;
        	}else{
        		toastr["success"]("Dodavanje koautora zavrseno");
        		top.location.href = "NC.html";
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Greska!');
        } 
        });
	
}
