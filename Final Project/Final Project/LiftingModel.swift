//
//  LiftingModel.swift
//  a03
//
//  Created by Elevate App on 6/12/21.
//

import UIKit

class LiftingModel {
    
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
    func initializeLiftingStats() {
        let addData = LiftingViewEntity(context: context)
        addData.reps1 = "0"
        addData.reps2 = "0"
        addData.reps3 = "0"
        addData.weight1 = "0"
        addData.weight2 = "0"
        addData.weight3 = "0"
        
        do {
            try context.save()
        } catch {
            //error
        }
    }
    
    func updateData() {
        do {
            try context.save()
        } catch {
            //error
        }

    }
    
}
