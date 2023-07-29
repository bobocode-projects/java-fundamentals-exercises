let head = createLinkedList([1, 2, 3, 4, 5])
printLinkedList(head)
console.log("----------------");
printReversedRecursively(head); // should print "5 -> 4 -> 3 -> 2 -> 1"

console.log("----------------");
let newHead = reverseLinkedList(head);
printLinkedList(newHead);// should print "5 -> 4 -> 3 -> 2 -> 1"

function createLinkedList(arr) {
    let head = createNode(arr[0])
    let current = head
    for (let i = 1; i < arr.length; i++) {
        current.next = createNode(arr[i])
        current = current.next
    }
    return head
}

function createNode(value) {
    return {
        element: value,
        next: null
    }
}

function printLinkedList(head) {
    let current = head
    let str = ""
    while (current.next != null) {
        str += current.element + " -> "
        current = current.next;
    }
    str += current.element
    console.log(str)
}

function printReversedRecursively(head) {
printReversedRecursivelyHelper(head)
}

function printReversedRecursivelyHelper(head) {
    if (head != null) {
        printReversedRecursivelyHelper(head.next)
        console.log(head.element)
    }
}

function reverseLinkedList(head) {
    let prev = head
    let current = head.next
    head.next = null
    while (current != null) {
        let next = current.next
        current.next = prev
        prev = current
        current = next
    }
    return prev
}