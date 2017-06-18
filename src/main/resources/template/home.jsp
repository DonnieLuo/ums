<html>
    <head>
        <title>Home Page</title>
    </head>
    <link href="/js/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="/css/bootstrap/bootstrap-table.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap/bootstrap.min.js" ></script>
    <script type="text/javascript" src="/js/bootstrap/bootstrap-table.js" ></script>

    <script type="text/javascript">
        $(function () {

            userTable();
            $(".fixed-table-body").height(400);
        });
        function userTable() {
            var url = "/user/table";
            $('#userTable').bootstrapTable({
                method:'POST',
                dataType:'json',
                contentType: "application/x-www-form-urlencoded",
                cache: false,
                striped: true,
                sidePagination: "client",
                url:url,
                showColumns:false,
                pagination:true,
                queryParams: "",
                minimumCountColumns:2,
                pageNumber:1,
                pageSize: 5,
                pageList: [10, 25, 50, 100],
                uniqueId: "id",

                columns: [

                    {
                        field : 'firstName',
                        title : 'First Name',
                        align : 'center',
                        valign : 'middle'

                    },
                    {
                        field : 'lastName',
                        title : 'Last Name',
                        align : 'center',
                        valign : 'middle'

                    },
                    {
                        field : 'username',
                        title : 'User Name',
                        align : 'center',
                        valign : 'middle'
                    },
                    {
                        field : 'password',
                        title : 'Password',
                        align : 'center',
                        valign : 'middle'
                    },{
                        field : 'id',
                        title : 'Action(s)',
                        align : 'center',
                        valign : 'middle',
                        formatter:function(value,row,index){
                            var userId=value;

                            var innerHtml =
                                    '<a class="btn btn-info" href="/user/edit?id='+userId+'">Edit</a>' +
                                    '<button class="btn btn-info" onclick="removeUser('+userId+')" >Remove</button>';
                            return innerHtml;
                        }
                    }]
            });
        }
        function removeUser(userId) {
            $("#removeModal").modal('hide');
            $.ajax({
                url:"/user/delete",
                dataType:"json",
                async:true,
                data:{"id":userId},
                type:"POST",

                success:function(data){
                    $("#userTable").bootstrapTable('refresh');
                },
                error:function(){

                }
            });
        }

        function edit(id) {
            $('#id').val(id.toString());
            $('#hideform').attr("action", "/user/edit").submit();
        }

        function refreshTable() {
            $("#userTable").bootstrapTable('refresh');
        }
    </script>
    <style>
        .fixed-table-body { padding-bottom: 1%}
        .btn-info{margin-left: 1%}
        span.button-group {display: none}
    </style>
    <body>
    <div class="content-wrapper container">
        <div class="col-md-2">

        </div>
        <div class="col-md-8">
            <div class="panel-body">
                <label class="text-center h2 center-block">Manage User:</label>
                <div class="form-group">

                    <a type="button" href="/user/add" style="margin-left: 50%;"
                       class="btn btn-primary btn-sm" >NEW USER</a>


                </div>
                <div class="form-group">
                    <table id="userTable" class="table table-hover">

                    </table>
                </div>

            </div>
        </div>
        <div class="col-md-2">

        </div>
    </div>

    </body>
</html>