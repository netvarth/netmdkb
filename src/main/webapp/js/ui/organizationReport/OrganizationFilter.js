function FilterCheckbox(map,object) {

 this.result = function () {
	var table=$j('<table />');
	table.attr('class','stdtable');
	var tableheading=$j('<thead><tr><th colspan="4" style="text-align:center;border: 0;"></th></tr></thead>');
	table.append(tableheading);
	if(map!=null) {

		var tbody=$j('<tbody/>');
		var count=0;
			var row=$j('<tr> <td style="text-align:left; border: 0;"><input type="checkbox" class="selectAll" id="selectAll'+object+'" name="selectAll"/>&nbsp&nbsp<label style="font-size:14px;font-family:lucida grande,arial,tahoma,verdana,sans-serif !important">Select All</label></td><tr/>');
				tbody.append(row);
				var len = $j.map(map, function(n, i) { return i; }).length;
				var rowElement;
				$j.each( map, function( key, value ) {
			    	if((count%4)==0)	
						rowElement=$j('<tr/>');	
						var column=$j('<td style="text-align:left; border: 0;"><input type="checkbox" id="'+key+'" name="'+value+'" class="filter'+object+'" />&nbsp&nbsp<label style="font-size:14px;font-family:lucida grande,arial,tahoma,verdana,sans-serif !important">'+value+'</label></td>');				
						rowElement.append(column);
  
					if((count+1)%4==0 || (count+1)==len)
						tbody.append(rowElement);	
                    count++;						
			
				});	    
				table.append(tbody);		   	
	    }
		
		return table;
	};
	}

