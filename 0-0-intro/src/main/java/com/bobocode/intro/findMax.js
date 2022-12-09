function findMax(array) {
    let max;
    if (array) {
        max = array[0];
        for (let i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
    }
    return max;
}

console.log(findMax([1, 2, 34, 23, 22]));
