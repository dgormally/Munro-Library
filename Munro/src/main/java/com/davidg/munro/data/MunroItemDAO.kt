package com.davidg.munro.data

object MunroItemDAO {

    var munroItemsList: MutableList<MunroItem> = mutableListOf()
        private set

    fun addMunroItem(item: MunroItem) {
        munroItemsList.add(item)
    }

    fun removeUnwantedItems() {
        munroItemsList.removeAt(0)
        val iterator = munroItemsList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.hillCategory?.isBlank() == true) {
                iterator.remove()
            }
        }
    }

    fun resetData() {
        if (munroItemsList
                .isEmpty()
                .not()
        ) {
            clear()
        }
    }

    private fun clear() {
        munroItemsList.clear()
    }
}