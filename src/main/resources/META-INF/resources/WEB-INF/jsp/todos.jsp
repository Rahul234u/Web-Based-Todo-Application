<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
    <div class="container">
    <h2>Your Todos</h2>
    <table class = "table">
        <thead>
            <th>Description</th>
            <th>Target Date</th>
            <th>Status</th>
            <th></th>
            <th></th>
        </thead>
        <tbody>
            <c:forEach var="todo" items="${todos}">
            <tr>
                <td>${todo.description}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.status}</td>
                <td><a class = "btn btn-warning" href="delete-todo?id=${todo.id}">Delete</a></td>
                <td><a class = "btn btn-success" href="update-todo?id=${todo.id}">Update</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
        <a class = "btn btn-success" href="add-todo">Add Todo</a>
    </div>
<%@include file="common/footer.jspf"%>