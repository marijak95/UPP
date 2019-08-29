$(document).ready(function () {
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/getLoggedIn",
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
		url: "http://localhost:4242/casopis/getAll",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	if(data!=null) {
        		var tabelaCasopisi = $('#tabelaCasopisi');
        		var divCasopisi = $('#divCasopisi');
        		for(i=0;i<data.length;i++){
        			tabelaCasopisi.append('<tr><td>'+data[i].naziv+'</td><td>'+data[i].issnBroj+'</td><td><button style=\"margin-left:15%\" class=\"button-home\" onclick=\"dodajRad('+data[i].id+ ')\">Dodaj rad</button></td></tr>');
        			divCasopisi.append(tabelaCasopisi);
    	        	}
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
		url: "http://localhost:4242/korisnik/getTaskovi",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	var divTaskovi = $('#divTaskovi');
        	divTaskovi.append("<h2 style=\"margin-left:25%\"> <b>Aktivni taskovi: </b></h2>");
        	for(i=0;i<data.length;i++){
        		divTaskovi.append("<button style=\"margin-left:15%\" onclick=\"task(\'"+data[i].id+"\',\'" + data[i].name+ "\')\" class=\"button-home\">"+data[i].name+"</button>");
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
		url: "http://localhost:4242/korisnik/getRadDTO/"+taskId,
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	if(taskName=="Ispravka rada u skladu sa komentarima"){
	        	divGlavni = $('#divTaskovi');
	        	var str="";	
	        	divGlavni.append("<h3 style=\"margin-left:15%;\">Naslov rada: "+data[0].naslovRada+"</h3>");
	        	divGlavni.append("<h4 style=\"margin-left:15%;\">Autor rada: "+data[0].autorRada+"</h4>");
	        	
	        	for(i=0;i<data.length;i++){	        		
	        		divGlavni.append("<h4 style=\"margin-left:15%;\">Recenzija: \'"+i+"\'</h4>");
	        		divGlavni.append("<p style=\"margin-left:15%;\">Autor recenzije: "+data[i].autorRecenzije+"</p>");
	        		divGlavni.append("<p style=\"margin-left:15%;\">Komentar recenzije: "+data[i].komentarRecenzije+"</p>");	
	        	}
	        	
	        	str+="<input style=\"margin-left:15%;\" type=\"file\" style=\"height:50px;\" class=\"form-first-name form-control\" id=\"dodatiRadPutanja\"/>";
	        	str+="<button class=\"btn btn-primary\" style=\"margin-left:15%;\" type=\"button\" onclick=\"submitIspravkaRada(\'"+taskId + "\')\" class=\"button-home\">Dodaj novi pdf</button>";
	        	
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

function submitIspravkaRada(taskId){
	
	var putanjaNoviPdf = $("#dodatiRadPutanja").val();
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/rad/ispravka/"+taskId+"/noviPdf",
        type: "POST",
        contentType:"text/plain",
        data:putanjaNoviPdf,
        success: function () {
        	toastr["success"]("Ispravka rada je poslata!");
        	top.location.href="NC.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	//toastr['error']('Greska!');  
        }
    });
}
function dodajRad(idCasopisa){
		var process = "NaucnaCentrala";
		$.ajax({
			async: false,
			url: "http://localhost:4242/user/startProcess/"+process,
	        type: "GET",
	        dataType:"json",
	        crossDomain: true,
	        withCredentials: true,
	        headers: {  'Access-Control-Allow-Origin': '*' },
	        success: function (data) {
			      top.location.href = "dodavanjeRada.html?idCasopisa="+idCasopisa+"&PID="+data.processInstanceId;
			},
			error: function (jqxhr, textStatus, errorThrown) {
			    toastr['error']('Greska!');
			} 
		});
}

function logout(){
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/logout",
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