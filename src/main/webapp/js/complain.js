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
			{ data: 'complain' },

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

$('#table tbody').on('click', '.editbtn ', function(event) {


	var data = table.row($(this).parents('tr')).data();
	$('#updateModal').modal('show');

	$.ajax({
		url: backend_url + 'complains/' + data.id,
		type: 'get',
		success: function(data) {


			$('#updateCategory').val(data.complaincategory);
			$('#updateComplain').val(data.complain);

			$('#id').val(data.id);

		}
	})
});

$(document).on('submit', '#updateComplainForm', function(e) {
	e.preventDefault();
	var category = $('#updateCategory').val();
	var complain = $('#updateComplain').val();
	var id = $('#id').val();

	console.log(category)

	if (category != '' && complain != '') {
		$.ajax({
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			url: backend_url + 'complains/' + id,
			type: "put",
			data: JSON.stringify({
				complaincategory: category,
				complain: complain,


			}),
			success: function(data) {
				$('#updateComplainForm').trigger("reset");
				table.destroy();
				loadComplains();
				$('#updateModal').modal('hide');

			}
		});
	} else {
		alert('Fill all the required fields');
	}
});


$('#table tbody').on('click', '.deleteBtn', function(event) {
	var data = table.row($(this).parents('tr')).data();
	event.preventDefault();
	var id = $(this).data('id');
	if (confirm("Are you sure want to delete this Complain ? ")) {
		$.ajax({
			url: backend_url + 'complains/' + data.id,
			type: "delete",
			success: function(data) {

				table.destroy();
				loadComplains();

			}
		});
	} else {
		return null;
	}



})
