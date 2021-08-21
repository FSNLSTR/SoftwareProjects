//
//  ViewController.swift
//  a03
//
//  Created by Elevate App on 6/11/21.
//

import UIKit

class HomePageViewController: UIViewController {

    @IBOutlet weak var liftingTextView: UITextView!
    @IBOutlet weak var workOutsTextView: UITextView!
    @IBOutlet weak var upcomingTextView: UITextView!
    
    var myAppDelegate: AppDelegate?
    var myModel: plannerModel?
    
    //core data context
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
    var liftingData = [LiftingViewEntity]()
    
    let liftingModel = LiftingModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        getCoreData()
        getUpcomingWorkout()
        getweekWorkout()
        
        self.myAppDelegate = UIApplication.shared.delegate as? AppDelegate
        self.myModel = self.myAppDelegate?.myModel
    }

    func getCoreData() {
        
        
        do {
            liftingData = try context.fetch(LiftingViewEntity.fetchRequest())
            if liftingData.isEmpty{
                liftingModel.initializeLiftingStats()
            }
            let liftingStatsOne = liftingData.popLast()
            
            if liftingData.isEmpty || (liftingStatsOne?.reps1 == "0") {
                liftingTextView.text = "No lifting stats available"
            }else{
                liftingTextView.text = String(liftingStatsOne!.workOutType ?? "") + "\n"
                liftingTextView.text += "Rep 1: " + String(liftingStatsOne!.reps1 ?? "")
                liftingTextView.text += "                       Weight 1: " + String((liftingStatsOne!.weight1 ?? "")) + "\n"
                
                //let liftingStatsTwo = liftingData.popLast()
                liftingTextView.text += "Rep 2: " + String(liftingStatsOne!.reps2 ?? "")
                liftingTextView.text += "                       Weight 2: " + String(liftingStatsOne!.weight2 ?? "") + "\n"
                
                //let liftingStatsThree = liftingData.popLast()
                liftingTextView.text += "Rep 3: " + String(liftingStatsOne!.reps3 ?? "")
                liftingTextView.text += "                       Weight 3: " + String(liftingStatsOne!.weight3 ?? "") + "\n"
            }
            
        } catch {
            //error
        }
        liftingData.removeLast()
    }
    func getUpcomingWorkout(){
        upcomingTextView.text = ""
        let tomorrow = Date().dayAfter
        let calendar = Calendar.current
        let comp = calendar.dateComponents([.day,.month,.year], from: tomorrow)
        
        for i in myModel?.plannerArray[comp] ?? []{
            upcomingTextView.text += i + "\n"
        }
    }
    
    func getweekWorkout(){
        workOutsTextView.text = ""
        var today = Date()
        let calendar = Calendar.current
        
        for _ in 1...7{
            today = today.dayAfter
            let comp = calendar.dateComponents([.day,.month,.year], from: today)
            for i in myModel?.plannerArray[comp] ?? []{
                workOutsTextView.text += i + "\n"
            }
            
        }
        
    }
    
    func reloadData() {
        getCoreData()
        getUpcomingWorkout()
        getweekWorkout()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        reloadData()
    }
}

extension Date {
    var dayAfter: Date {
        return Calendar.current.date(byAdding: .day, value: 1, to: self)!
    }

    var dayBefore: Date {
        return Calendar.current.date(byAdding: .day, value: -1, to: self)!
    }
}

