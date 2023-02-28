//
//  SubmitButton.swift
//  GPT Client
//
//  Created by Tobias Wissmüller on 27.02.23.
//

import SwiftUI

func SubmitButton(disabled: Bool, action: @escaping () -> Void) -> some View {
    Button(action: action) {
        Text("Send")
    }
    .padding()
    .foregroundColor(.white)
    .background(disabled ? Color.gray : Color.blue)
    .cornerRadius(8)
    .disabled(disabled)
}
