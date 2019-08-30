$(document).ready(function () {
	
	$.ajax({
		async: false,
		url: "http://localhost:3000/korisnik/getLoggedIn",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	if(data!=null) {
        	} else {
        		top.location.href = "home.html";
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	top.location.href = "home.html";                
        }
    });
	$.ajax({
		async: false,
		url: "http://localhost:3000/korisnik/getTaskovi",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	var divTaskovi = $('#divTaskovi');
        	divTaskovi.append("<h2 style=\"margin-left:15%\"><b>Aktivni taskovi: </b> </h2>");
        	for(i=0;i<data.length;i++){
        		divTaskovi.append("<button style=\"margin-left:15%\" onclick=\"task(\'"+data[i].id+"\',\'" + data[i].name+ "\')\" class=\"btn btn-primary\">"+data[i].name+"</button>");
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr["error"]('Greska');              
        }
    });
})

function task(taskId,taskName){
	$.ajax({
		async: false,
		url: "http://localhost:3000/korisnik/getFormFields/"+taskId,
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	if(taskName=="Recenziranje"){
	        	divGlavni = $('#divTaskovi');
	        	var str="";	
	        	divGlavni.append("<h3 style=\"margin-left:15%;\">Naslov rada: "+data.naslovRada+"</h3>");
	        	divGlavni.append("<h4 style=\"margin-left:15%;\">Autor rada: "+data.autorRada+"</h4>");
	        	for(i=0;i<data.formField.length;i++){
		        		str+="<div class=\"form-group\">";
		        		str+="<label style=\"margin-left:15%;\">"+data.formField[i].label+"</label>";
		        		str+="<input style=\"margin-left:15%; width:150px;\" type=\"text\" placeholder="+data.formField[i].label+" class=\"form-first-name form-control\" id=\"komentarRecenzije\"/>";
		        		str+="</div>";
		        		divGlavni.append(str);
		        		
		        		str="";
	        		}
	        	str+=" <button style=\"margin-left:15%;\" onclick=\"dodajRecenziju(\'"+taskId + "\')\" class=\"button-home\">Dodaj recenziju</button>";
	        	
	        	str+="<input type=\"hidden\" id=\"taskId\" name=\"taskId\" value=\""+data.taskId+"\">";
				str+="<input type=\"hidden\" id=\"processInstanceId\" name=\"processInstanceId\" value=\""+data.processInstanceId+"\">";
				
	        	divGlavni.append(str);
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Greska!');
        } 
        });	
}

function dodajRecenziju(taskId){
	var komentarRecenzije = $("#komentarRecenzije").val();
	
	var recenzijaDTO = JSON.stringify({
		"komentar":komentarRecenzije,
	});
	
	$.ajax({
		async: false,
		url: "http://localhost:3000/korisnik/submitRecenziranje/"+taskId,
        type: "POST",
        contentType:"application/json",
        data:recenzijaDTO,
        success: function () {
        	toastr["success"]("Recenziranje uspesno!");
        	top.location.href="homeRecenzent.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr["error"]('Greska');              
        }
    });
}

function logout(){
	
	$.ajax({
		async: false,
		url: "http://localhost:3000/korisnik/logout",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	top.location.href = "home.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	top.location.href = "home.html";                
        }
    });
	
}