google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  $.ajax({
    			url:'Diario',
    			data:{dia:'2014-11-04'},
    			type:'POST',
    			success:function(data){
    			
    	 $("$result").html(data);
    			
    	  // Create and populate the data table.
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