//
//  CalendarViewController.swift
//  Final Project
//
//  Created by Sunil Sakthivel on 6/18/21.
//

import UIKit

class CalendarViewController: UIViewController {
    
    @IBOutlet weak var calendarPick: UIDatePicker!
    
    var myAppDelegate: AppDelegate?
    var myModel: plannerModel?
    @IBOutlet weak var workoutPresenter: UITextView!
    
    
    @objc func datePickerChanged(sender: UIDatePicker) {
        
    }
    
    @IBAction func datePickerChanged(_ sender: Any) {
        workoutPresenter.text = ""
        let dateC = calendarPick.calendar.dateComponents([.day, .month, .year], from: calendarPick.date)
        print(myModel?.plannerArray[dateC])
        if ((myModel?.plannerArray.keys.contains(dateC)) != nil){
            let k  = myModel?.plannerArray[dateC]
            for i in k ?? []{
                workoutPresenter.text! += i+"\n"
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        calendarPick.datePickerMode = .date
        self.myAppDelegate = UIApplication.shared.delegate as? AppDelegate
        self.myModel = self.myAppDelegate?.myModel
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        viewDidLoad()
    }
    
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
