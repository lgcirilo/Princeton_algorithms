var myArr = [1, 2, 3, 4, 5];
console.log.apply(console, myArr.map(function (ele) { return ele; }));
