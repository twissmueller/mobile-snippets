//
//  InputText.swift
//  GPT Client
//
//  Created by Tobias Wissmüller on 27.02.23.
//

import SwiftUI

func InputText(text: Binding<String>, disabled: Bool, onTap: @escaping () -> Void) -> some View {
    TextField("Enter text", text: text)
        .padding()
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .strokeBorder(disabled ? Color.gray : Color.blue, style: StrokeStyle(lineWidth: 1.0))
        )
        .onTapGesture {
            text.wrappedValue = ""
            onTap()
        }
}
