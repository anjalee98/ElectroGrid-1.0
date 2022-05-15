var backend_url = 'http://localhost:8080/ElectroGrid/rest/';
$(document).ready(function() {

	loadComplains();

});

var table = null;
function loadComplains() {
	table = $('#table').DataTable({
		'processing': 'true',
		'paging': 'true',
		ajax: {
			'url': backend_url + 'complains',
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
}
$(document).on('submit', '#addComplain', function(e) {
	e.preventDefault();
	var category = $('#category').val();
	var complain = $('#complain').val();

	if (category != '' && complain != '') {
		$.ajax({
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			url: backend_url + 'complains',
			type: "post",
			data: JSON.stringify({
				complaincategory: category,
				complain: complain,

			}),
			success: function(data) {
				$('#addComplain').trigger("reset");
				table.destroy();
				loadComplains();
				$('#addComplainModal').modal('hide');

			}
		});
	} else {
		alert('Fill all the required fields');
	}
});