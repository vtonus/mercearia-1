google.load("visualization", "1", {packages:["corechart"]});
   //   google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  $.ajax({
    			url:'Diario',
    			data:$("#formulario").serialize(),//{dia:'2014-11-04'},
    			type:'POST',
    			success:function(data){
    				$(".diario .dados").show();
    	 $("#result").html(data);      	  
    	  var caixa="<table style='width:200px'>"+
			"<tr >"+
	"		<th colspan='2' style='text-align:center; width:100%'>Caixa</th>"+
"		</tr>"+
"		<tr>"+
		"	<td>Abriu</td>"+
	"		<td>R$"+$("#abriu").val()+"</td>"+
"		</tr>"+
"		<tr>"+
"			<td>Fechou</td>"+
"			<td>R$"+$("#fechou").val()+"</td>"+
"		</tr>"+
"		</table>"+
"			<table style='width:200px'>"+
"		<tr >"+
"			<th colspan='2' style='text-align:center; width:100%'>Vendas</th>"+
"		</tr>"+
"			<tr>"+
"			<td>com Cartão</td>"+
"			<td>R$"+$("#cartao").val()+"</td>"+
	"	</tr>"+
"			<tr>"+
	"		<td>com Dinheiro</td>"+
	"		<td>R$"+$("#dinheiro").val()+"</td>"+
	"	<tr>"+
	"		<td>a prazo </td>"+
	"		<td>R$"+$("#prazo").val()+"</td>"+
	"	</tr>"+
	"	</tr>"+
	"</table>";
    	  
    	  $(".caixa").html(caixa);
    	  console.log("ma passou aqui hehe");   
    	
    	  var media= "<table><tr><th>Media Mensal</th></tr><tr><td>R$"+$("#mmensal").val()+"</td></tr></table>";
    	  $(".mediamensal").html(media);
    	  //o outro chart dos valores
    	  
    	  
    	  // Create and populate the data table Segunda tabela do google chart
    	  var data2 = new google.visualization.DataTable();
    	 
    	 data2.addColumn('string', 'hora');
    	 data2.addColumn('number', 'valor');
    	 var i=0;
    	 var array="[";
    	  while($("#valor" + i).val()!=''){
    		  array+='["'+i+'H",'+$('#valor'+i).val()+'],';
        	  i++;
        	  if(i==23){
        		  break;
        		}
    	  }
    	  array+='["'+23+'H",'+$('#valor'+23).val()+']]';
    	  console.log(array);
    	  data2.addRows(JSON.parse(array));
    	  // Create and draw the visualization.
    	  new google.visualization.AreaChart(document.getElementById('chart_div2')).
  	      draw(data2, {});
    	  
    	  
    	  
    			}
    	});
      }
      
      
      function drawChart2() {
    	  $.ajax({
    			url:'Diario',
    			data:{dia:'2014-11-04'},
    			type:'POST',
    			success:function(data){
    			
    	 $("#result").html(data);
    			
    	  // Create and populate the data table
    	  var years = ['2001', '2002', '2003', '2004', '2005'];
    	  var sales = [1, 2, 3, 4, 5];

    	  var data = new google.visualization.DataTable();
    	 
    	  data.addColumn('string', 'years');
    	  data.addColumn('number', 'sales');

    	  for(i = 0; i < years.length; i++)
    	    data.addRow([years[i], sales[i]]);

    	  while($("#dia" + i).value()!=''){
        	  data.addColumn( $("#dia" + i).value(),$("#venda" + i).value() );
        	  i++;
        	 }
    	  // Create and draw the visualization.
    	  new google.visualization.AreaChart(document.getElementById('chart_div')).
    	    draw(data, {});
    	  new google.visualization.AreaChart(document.getElementById('chart_div2')).
  	      draw(data, {});
    			}
    	});
      }
      
      
 function mudaview1(){
    	  $( ".some" ).hide();
    	  $(".diario").show();
    	  console.log("oi");	  
	  
  }
 
 function mudaview2(){
	  $( ".some" ).hide();
	  $(".periodo").show();
	  console.log("oi2");	  
 
}