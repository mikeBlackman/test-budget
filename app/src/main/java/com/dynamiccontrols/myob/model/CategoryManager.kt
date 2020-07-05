package com.dynamiccontrols.myob.model

import com.dynamiccontrols.myob.R
import javax.inject.Inject


interface CategoryManager {
    fun generateCategorySet(): Set<Category>
    fun getCategoryByName(name: String): Category
}

class CategoryManagerImpl @Inject constructor() :
    CategoryManager {

    private val categorySet = mutableSetOf<Category>()

    override fun generateCategorySet(): Set<Category> {
        if (categorySet.isEmpty()) {
            with(categorySet) {
                add(Category("Groceries",
                    R.color.yellow,
                    R.color.black
                ))
                add(Category("Bills",
                    R.color.purple,
                    R.color.black
                ))
                add(Category("Transport & Auto",
                    R.color.dark_blue,
                    R.color.black
                ))
                add(Category("Eating Out",
                    R.color.pink,
                    R.color.black
                ))
                add(Category("Shopping",
                    R.color.light_green,
                    R.color.black
                ))
                add(Category("Education",
                    R.color.orange,
                    R.color.black
                ))
                add(Category("Entertainment",
                    R.color.dark_orange,
                    R.color.black
                ))
                add(Category("Investments",
                    R.color.light_green,
                    R.color.black
                ))
                add(Category("Home Improvement",
                    R.color.red,
                    R.color.black
                ))
                add(Category("Mortgage / Rent",
                    R.color.green,
                    R.color.black
                ))
                add(Category("Travel",
                    R.color.grey,
                    R.color.black
                ))
                add(Category("Other",
                    R.color.grey,
                    R.color.black
                ))
            }
        }
        return categorySet
    }

    override fun getCategoryByName(name: String): Category {
        return generateCategorySet().first {
            it.toString() == name
        }
    }

}