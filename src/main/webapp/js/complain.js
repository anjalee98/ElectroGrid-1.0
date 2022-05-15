 var backend_url='http://localhost:8080/ElectroGrid/rest/';
 $(document).ready(function() {
	
$('#table').DataTable({
        ajax: {
            'url': backend_url+'complains',
          'type': 'get',
          dataSrc: '',
        },
        columns: [
          { data: 'id' },
            { data: 'complaincategory' },
            { data: 'complain' }
        ],
        columnDefs: [
            {
                targets: 3,
                data: null,
                defaultContent: '<a href="#!"   class="btn btn-warning btn-sm editbtn">Edit</a>  <a href="#!"   class="btn btn-danger btn-sm deleteBtn">Delete</a>',
            },
        ],
    });
    
   
    });