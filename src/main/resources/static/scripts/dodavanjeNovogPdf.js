$(document).ready(function () {
	var url_string = window.location.href;
	var url = new URL(url_string);
	var pid = url.searchParams.get("PID");
	var idRada = url.searchParams.get("idRada");
	var naucneOblasti;
	$.ajax({
			async: false,
			url: "http://localhost:4242/user/getFormFields/"+pid,
	        type: "GET",
	        dataType: "json",
	        success: function (data) {
	        	divGlavni = $('#formaRad');
	        	var str="";
	        	
	        	for(i=0;i<data.formField.length;i++){
	        		str+="<div class=\"form-group\">";
	        		str+="<label>"+data.formField[i].label+"</label>";
	        		str+="<input type=\"file\" style=\"height:50px;\"  placeholder="+data.formField[i].label+" class=\"form-first-name form-control\" id="+data.formField[i].id+">";
	        		str+="</div>";
	        		divGlavni.append(str);
	        		str="";
	        	}
	        	str+=" <button type=\"button\" onclick=\"submitIspravkaRada()\" class=\"button-home\">Dodaj</button>";
	        	
	        	str+="<input type=\"hidden\" id=\"taskId\" name=\"taskId\" value=\""+data.taskId+"\">";
				str+="<input type=\"hidden\" id=\"processInstanceId\" name=\"processInstanceId\" value=\""+data.processInstanceId+"\">";
				
	        	divGlavni.append(str);
	        },
	        error: function (jqxhr, textStatus, errorThrown) {
	        	toastr['error']('Greska!');
	            
	        }
	    });
});

function submitIspravkaRada(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var idRada = url.searchParams.get("idRada");
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PID");
	
	putanjaNoviPdf = $("#putanjaNoviPdf").val();
	
	var data = JSON.stringify([
	              {"fieldId":"putanjaNoviPdf",
	            	  "fieldValue":putanjaNoviPdf},   
	            ]);
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/rad/dodajIspravku/"+PInsId+"/idRada/"+idRada,
        type: "POST",
        contentType: "application/json",
        data: data,
        success: function () {
        	toastr["success"]("Ispravka rada je poslata!");
        	top.location.href="NC.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Greska!');
            
        }
    });
	
}