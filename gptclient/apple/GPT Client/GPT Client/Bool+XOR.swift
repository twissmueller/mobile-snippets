//
//  Bool+XOR.swift
//  GPT Client
//
//  Created by Tobias Wissmüller on 27.02.23.
//

import Foundation

extension Bool {
    static func ^ (left: Bool, right: Bool) -> Bool {
        return left != right
    }
}
