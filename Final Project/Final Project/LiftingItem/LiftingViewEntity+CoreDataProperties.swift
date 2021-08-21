//
//  LiftingViewEntity+CoreDataProperties.swift
//  a02
//
//  Created by Elevate App on 6/13/21.
//
//

import Foundation
import CoreData


extension LiftingViewEntity {

    @nonobjc public class func fetchRequest() -> NSFetchRequest<LiftingViewEntity> {
        return NSFetchRequest<LiftingViewEntity>(entityName: "LiftingViewEntity")
    }

    @NSManaged public var reps1: String?
    @NSManaged public var weight1: String?
    @NSManaged public var reps2: String?
    @NSManaged public var weight2: String?
    @NSManaged public var reps3: String?
    @NSManaged public var weight3: String?
    @NSManaged public var workOutType: String?

}

extension LiftingViewEntity : Identifiable {

}
