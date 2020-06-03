<%@ include file="../../template/header.jsp"%>
<div class="container-fixed">
    <h2 class="underline">EMPLOYEE MENU</h2>
    <div class="row">
        <div class="col-l-2">
            <div class="content-list">
                <a class="content-list-item content-list-item-brand" href="/employee/wagon/list">
                    Wagon
                </a>
                <a class="content-list-item content-list-item-brand" href="/employee/drivers">
                    Drivers
                </a>
                <a class="content-list-item content-list-item-brand" href="/employee/cargo/list">
                    Cargos
                </a>
                <a class="content-list-item content-list-item-brand" href="/employee/order">
                    Orders
                </a>
            </div>
        </div>
        <div class="col-l-8" id="content">
            Choose...
        </div>
    </div>
</div>
<%@ include file="../../template/footer.jsp"%>