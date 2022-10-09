package compose
/*

val changingList = remember(state.list) { state.list.filter { it.shouldBeRendered } }
remember(key) - if the key has been changed, the value will be recalculated and recomposed.

val changingList by remember {
    derivedStateOf {
        state.list.filter { it.shouldBeRendered }
    }
}
Same result, but it will be recomposed only if result of filtering will be changed

*/