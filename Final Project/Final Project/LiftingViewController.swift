//
//  LiftingViewController.swift
//  a03
//
//  Created by Elevate App on 6/12/21.
//

import UIKit

class LiftingViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    
    var workoutPickerValues: [String] = [String]()
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return workoutPickerValues.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return workoutPickerValues[row]
    }
    
    var selection = "Bench Press"
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int){
        selection = workoutPickerValues[row] as String
        self.view.endEditing(true)
    }
    

    @IBOutlet weak var firstRepTextField: UITextField!
    @IBOutlet weak var secondRepTextField: UITextField!
    @IBOutlet weak var thirdRepTextField: UITextField!
    
    @IBOutlet weak var firstWeightTextField: UITextField!
    @IBOutlet weak var secondWeightTextField: UITextField!
    @IBOutlet weak var thirdWeightTextField: UITextField!
    
    @IBOutlet weak var workOutPickerView: UIPickerView!
    
    
    //var liftingRepsArray: [String] = []
    //var liftingWeightArray: [String] = []
    
    //core data context
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    let liftingModel = LiftingModel()
    
    var myAppDelegate: AppDelegate?
    var myModel: plannerModel?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        workOutPickerView.dataSource = self
        workOutPickerView.delegate = self
        
        workoutPickerValues = ["Bench Press", "Back Squat", "Dead Lift", "Power Clean", "Leg Curls", "Incline Bench"]
        
        self.tabBarController?.selectedIndex = 1
        
        self.myAppDelegate = UIApplication.shared.delegate as? AppDelegate
        self.myModel = self.myAppDelegate?.myModel
        
    }
    
    @IBAction func submitLiftButton(_ sender: Any) {
        let addData = LiftingViewEntity(context: context)
        
        //liftingModel.initializeLiftingStats()
        
        if (firstRepTextField.text != "" && firstWeightTextField.text != "") {
            addData.reps1 = firstRepTextField.text!
            addData.weight1 = firstWeightTextField.text!
            
            firstRepTextField.text = ""
            firstWeightTextField.text = ""
            
            do {
                try context.save()
            } catch {
                //error
            }
            if (secondRepTextField.text != "" && secondWeightTextField.text != "") {
                addData.reps2 = secondRepTextField.text!
                addData.weight2 = secondWeightTextField.text!
                
                secondRepTextField.text = ""
                secondWeightTextField.text = ""
                
                do {
                    try context.save()
                } catch {
                    //error
                }
            }
            if (thirdRepTextField.text != "" && thirdWeightTextField.text != "") {
                addData.reps3 = thirdRepTextField.text!
                addData.weight3 = thirdWeightTextField.text!
                
                thirdRepTextField.text = ""
                thirdWeightTextField.text = ""
                
                do {
                    try context.save()
                    //homePageFuncs.getCoreData()
                } catch {
                    //error
                }
            }
            
            addData.workOutType = selection
            
            do {
                try context.save()
                //homePageFuncs.getCoreData()
            } catch {
                //error
            }
            
            
        }
        //print(liftingStatsArray)
        
    }
    

}

