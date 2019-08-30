$(document).ready(function () {
	
	$.ajax({
		async: false,
		url: "http://localhost:3000/rad/getAllIspravljeniRadovi",
        type: "GET",
        dataType: "json",
        success: function (data) {
    	        	if(data!=null) {
    	        		var tabelaRadovi = $('#tabelaRadovi');
    	        		var divRadovi = $('#divRadovi');
    	        		for(i=0;i<data.length;i++){
    	        			tabelaRadovi.append('<tr><td>'+data[i].naslov+'</td><td>'+data[i].apstrakt+'</td><td>'+data[i].kljucniPojmovi+'</td><td><button style=\"margin-left:15%\" class=\"button-home\" onclick=\"ispravi('+data[i].id + ",\'" + data[i].pid+ '\')\">Ispravi</button></td></tr>');
    	        			divRadovi.append(tabelaRadovi);
    	    	        	}
    	        	} else {
    	        		toastr["error"]('Greska');
    	        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr["error"]('Greska');              
        }
    });
})

function ispravi(id, pid){
	top.location.href = "dodavanjeNovogPdf.html?PID=" + pid + "&idRada=" +id;
}
