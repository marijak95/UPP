$(document).ready(function () {
	var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PID");
	var naucneOblasti;
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var idRada = url.searchParams.get("idRada");
	
	var divPdf = $('#divPdf');
	divPdf.append("<button style=\"margin-left:15%\" class=\"button-home\" id=\"prihvatiRad\" onclick=\"prihvatiRad("+idRada + ",\'" + PInsId + "\')\">Prihvati</button>");
	divPdf.append("<button style=\"margin-left:5%\" class=\"button-home\" onclick=\"korekcijaFormata("+idRada + ",\'" + PInsId + "\')\">Korekcija formata</button>");
	
});

function prihvatiRad(idRada,PID){
	$.ajax({
		async: false,
		url: "http://localhost:3000/rad/prihvatiRad/"+PID+"/radId/"+idRada,
        type: "GET", 
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function () {
        	toastr["success"]("Rad je prihvacen i poslat na recenziju.");
        	top.location.href="homeGlavni.html";
		},
		error: function (jqxhr, textStatus, errorThrown) {
		    toastr['error']('Greska');
		} 
	});
	
}

function korekcijaFormata(idRada,PID){
	$.ajax({
		async: false,
		url: "http://localhost:3000/rad/korekcijaFormata/"+PID+"/radId/"+idRada,
        type: "GET", 
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function () {
        	top.location.href="homeGlavni.html";
		},
		error: function (jqxhr, textStatus, errorThrown) {
		    toastr['error']('Greska');
		} 
	});
	
}