<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h2>Create Todo</h2>
    <form:form method="post" modelAttribute="todo">

        <fieldset class="mb-3">
            <form:label path="description">Description : </form:label>
            <form:input type="text" path="description"/>
            <form:errors path="description" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="mb-3">
            <form:label path="targetDate">TargetDate : </form:label>
            <form:input type="date" path="targetDate" id = "targetDate"/>
            <form:errors path="targetDate" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="mb-3">
            <form:label path="status">Status : </form:label>
            <form:input type="text" path="status"/>
            <form:errors path="status" cssClass="text-warning"/>
        </fieldset>

        <form:input type="hidden" path="id"/>

        <input type="submit" value="Submit" class="btn btn-success">

    </form:form>
</div>
<%@include file="common/footer.jspf"%>