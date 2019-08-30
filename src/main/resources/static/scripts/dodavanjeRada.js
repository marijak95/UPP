$(document).ready(function () {
	var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PID");
	var naucneOblasti;
	
	$.ajax({
		async: false,
		url: "http://localhost:3000/naucnaOblast/getAll",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	var naucneOblastiSelect = $("#naucneOblastiSelect");
        	if(data!=null){
        		for(i = 0; i < data.length ; i++){
        			naucneOblastiSelect.append("<option id=\""+data[i].id + "\" value=\""+data[i].id + "\">" + data[i].naziv + "</option>");
        		}
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Greska!');
            
        }
    });
	$.ajax({
		async: false,
		url: "http://localhost:3000/user/getFormFields/"+PInsId,
        type: "GET",
        dataType: "json",
        success: function (data) {
        	divGlavni = $('#formaRad');
        	var str="";
        	
        	for(i=0;i<data.formField.length;i++){
        		str+="<div class=\"form-group\">";
        		if(data.formField[i].label!="naucnaOblast" && data.formField[i].label!="idCasopisa"){
        			str+="<label>"+data.formField[i].label+"</label>";
            		
        			if(data.formField[i].label == "Kljucni pojmovi"){
	        			str+="<input type=\"text\"  placeholder=\"Kljucni pojmovi\" class=\"form-first-name form-control\" id="+data.formField[i].id+">";
        			
	        		}else{
		        		if(data.formField[i].label == "Putanja do PDF"){
		        			str+="<input type=\"file\" style=\"height:50px;\"  placeholder="+data.formField[i].label+" class=\"form-first-name form-control\" id="+data.formField[i].id+">";
		        		}
		        		else{
		        			str+="<input type=\"text\"  placeholder="+data.formField[i].label+" class=\"form-first-name form-control\" id="+data.formField[i].id+">";
		        		}
		        	}
	        		
	        		str+="</div>";
	        		divGlavni.append(str);
	        		str="";
	        	}
        	}
        	str+=" <button type=\"button\" onclick=\"submitDodavanjeRada()\" class=\"button-home\">Dodaj</button>";
        	
        	str+="<input type=\"hidden\" id=\"taskId\" name=\"taskId\" value=\""+data.taskId+"\">";
			str+="<input type=\"hidden\" id=\"processInstanceId\" name=\"processInstanceId\" value=\""+data.processInstanceId+"\">";
			
        	divGlavni.append(str);
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Greska!');
            
        }
    });
})

function submitDodavanjeRada(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var idCasopisa = url.searchParams.get("idCasopisa");
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PID");
	
	naslov = $("#naslov").val();
	apstrakt = $("#apstrakt").val();
	kljucniPojmovi = $("#kljucniPojmovi").val();
	putanjaDoPDF = $("#putanjaDoPdf").val();
	naucnaOblast = $("#naucneOblastiSelect").val();
	taskId = $("#taskId").val();
	
	var data = JSON.stringify([
	              {"fieldId":"naslov",
	            	  "fieldValue":naslov},
	            	  {"fieldId":"apstrakt",
		            	  "fieldValue":apstrakt},
		            	  {"fieldId":"kljucniPojmovi",
			            	  "fieldValue":kljucniPojmovi},
			            	  {"fieldId":"putanjaDoPDF",
				            	  "fieldValue":putanjaDoPDF},
				            	  {"fieldId":"naucnaOblast",
					            	  "fieldValue":naucnaOblast},
					            	  {"fieldId":"idCasopisa",
						            	  "fieldValue":idCasopisa},   
	            ]);
	
	$.ajax({
		async: false,
		url: "http://localhost:3000/rad/dodajRad/"+taskId,
        type: "POST",
        contentType: "application/json",
        data: data,
        success: function () {
        	toastr["success"]("Rad je uspesno dodat!");
        	top.location.href = "dodavanjeKoautora.html?PID=" + PInsId;    
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Greska!');
            
        }
    });
}
