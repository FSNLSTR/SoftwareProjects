//
//  ReminderViewController.swift
//  a03
//
//  Created by Sunil Sakthivel on 6/14/21.
//

import UIKit
import UserNotifications

class ReminderViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    
    var dayWorkoutPickerValues: [String] = [String]()
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        dayWorkoutPickerValues.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return dayWorkoutPickerValues[row]
    }
    
    var selection = "Chest and Biceps"
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int){
        selection = dayWorkoutPickerValues[row] as String
        self.view.endEditing(true)
    }
    
    
    @IBOutlet weak var reminderPicker: UIDatePicker!
    @IBOutlet weak var dayWorkoutPicker: UIPickerView!
    
    var myAppDelegate: AppDelegate?
    var myModel: plannerModel?
    
    @IBAction func reminderNotification(_ sender: UIButton) {
        let components = reminderPicker.calendar.dateComponents([.minute,.hour,.day, .month, .year], from: reminderPicker.date)
        

        timedNotifications(dComponent: components) { (success) in
            if success{
                print("Successfully Notified")
            }
        }
        
    }
    
    func timedNotifications(dComponent: DateComponents, completion: @escaping(_ Success: Bool) -> ()){
    
        let trigger = UNCalendarNotificationTrigger(dateMatching: dComponent, repeats: true) // Repeats everyday
        
        let content = UNMutableNotificationContent()
        
        content.title = "LETS GO WORKOUT!"
        content.body = "Go get your " + selection + " workout done"

        
        let request = UNNotificationRequest(identifier: "customNotification", content: content, trigger: trigger)
        
        UNUserNotificationCenter.current().add(request) { (error) in
            if error != nil{
                completion(false)
            }else{
                completion(true)
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .badge, .sound]){ (success, error) in
            
            if error != nil{
                print("Authorization Unsuccessfull")
            }else{
                print("Authorization Successfull")
            }
        }
        
        

        // Do any additional setup after loading the view.
        dayWorkoutPicker.dataSource = self
        dayWorkoutPicker.delegate = self
        
        dayWorkoutPickerValues = ["Chest and Biceps", "Back and Triceps", "Cardio", "Leg", "Abs", "Shoulder"]
        
        self.myAppDelegate = UIApplication.shared.delegate as? AppDelegate
        self.myModel = self.myAppDelegate?.myModel
    }
    
    @IBAction func scheduleWorkout(_ sender: Any) {
        let dComp = reminderPicker.calendar.dateComponents([.day, .month, .year], from: reminderPicker.date)
        if (myModel?.plannerArray.keys.contains(dComp))!{
            myModel?.plannerArray[dComp]?.append(selection)
        }else{
            myModel?.plannerArray[dComp] = [selection]
        }
        myModel?.planner.append(selection)
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
