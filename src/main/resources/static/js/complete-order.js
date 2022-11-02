function totalSum() {
    let amount = 0;
    let totals = document.getElementsByClassName('total');

    Array.from(totals).forEach(t => {
        amount = Number(t.textContent) + Number(amount);
    })

    let totalSum = document.getElementById("cartTotalPrice");
    totalSum.textContent = amount.toFixed(2) + "";
}

totalSum();

